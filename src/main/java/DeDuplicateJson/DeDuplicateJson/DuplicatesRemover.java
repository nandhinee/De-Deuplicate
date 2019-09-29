package DeDuplicateJson.DeDuplicateJson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DuplicatesRemover {
	// logger
	final static Logger log = Logger.getLogger(DuplicatesRemover.class);

	Set<Lead> result = new HashSet<Lead>();
	public void removeDuplicates(String inputFilePath, String outputFilePath) {
		// mapper to process json
		ObjectMapper mapper = new ObjectMapper();

		// read from json file
		ProcessJson processJson = new ProcessJson();

		Leads leadsObj = null;
		try {
			leadsObj = processJson.readJsonFromFile(mapper, inputFilePath);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// clear the set to remove leads from previous call
		result.clear();
		
		// contains unique leads by comparing id and email
		Set<Lead> set = new HashSet<Lead>();
		if (leadsObj != null && leadsObj.leads.size() > 0) {

			for (Lead curr : leadsObj.leads) {
				// process only if id, email, date are not null
				if (curr._id != null && !curr._id.isEmpty() && curr.email != null && !curr.email.isEmpty()
						&& curr.entryDate != null) {
					if (!set.contains(curr)) {
						addLeadToResult(set, result, curr, null);
					} else {
						List<Lead> dups = new ArrayList<Lead>();
						// get all duplicates that have id or email equal to current lead into a list
						for (Lead lead : set) {
							if (lead.equals(curr)) {
								dups.add(lead);
							}
						}
						// iterate through each of the duplicates and compare it with new lead
						for (Lead dup : dups) {
							if (dup._id.equals(curr._id)) {
								log.info("id is duplicate: " + dup._id);
							}
							if (dup.email.equals(curr.email)) {
								log.info("email is duplicate: " + dup.email);
							}
							// skip is new lead id older than existing lead
							if (dup.entryDate.after(curr.entryDate)) {
								log.info("duplicate encountered but skipping since new lead " + curr._id
										+ "is older than lead " + dup._id);
								continue;
							} else {
								try {
									log.info("source lead: " + mapper.writeValueAsString(dup));
								} catch (JsonProcessingException e) {
									log.error(e.getStackTrace());
								}
								try {
									log.info("new lead: " + mapper.writeValueAsString(curr));
								} catch (JsonProcessingException e) {
									log.error(e.getStackTrace());
								}
								addLeadToResult(set, result, curr, dup);
								logDifferences(dup, curr);
							}
						}
					}
				}
			}
		}
		// write to output file
		Leads resultLeads = new Leads();
		resultLeads.leads = new ArrayList<Lead>(result);
		try {
			processJson.writeToOutput(mapper, outputFilePath, resultLeads);
		} catch (Exception e) {
			log.error(e.getStackTrace());
		}
	}

	private void logDifferences(Lead prev, Lead curr) {
		if (!prev._id.equals(curr._id))
			log.info("lead id changed from " + prev._id + " to " + curr._id);
		if (!prev.email.equals(curr.email))
			log.info("lead email changed from " + prev.email + " to " + curr.email);
		String prevName = prev.firstName == null ? "null" : prev.firstName;
		String currName = curr.firstName == null ? "null" : curr.firstName;
		if (!prevName.equals(currName))
			log.info("lead firstname changed from " + prevName + " to " + currName);
		String prevLName = prev.lastName == null ? "null" : prev.lastName;
		String currLName = curr.lastName == null ? "null" : curr.lastName;
		if (!prevLName.equals(currLName))
			log.info("lead lastname changed from " + prevLName + " to " + currLName);
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		if (!prev.entryDate.equals(curr.entryDate))
			log.info("lead entrydate changed from " + sdf.format(prev.entryDate) + " to " + sdf.format(curr.entryDate));
		String preAdd = prev.address == null ? "null" : prev.address;
		String currAdd = curr.address == null ? "null" : curr.address;
		if (!preAdd.equals(currAdd))
			log.info("lead address changed from " + preAdd + " to " + currAdd);
	}

	private void addLeadToResult(Set<Lead> set, Set<Lead> leadsToPrint, Lead curr, Lead prev) {
		if (prev != null) {
			set.remove(prev);
			leadsToPrint.remove(prev);
		}
		set.add(curr);
		leadsToPrint.add(curr);
	}

}
