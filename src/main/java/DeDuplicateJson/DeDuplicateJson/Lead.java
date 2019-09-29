package DeDuplicateJson.DeDuplicateJson;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Lead {

	@JsonProperty("_id")
	String _id;

	@JsonProperty("email")
	String email;

	@JsonProperty("firstName")
	String firstName;

	@JsonProperty("lastName")
	String lastName;

	@JsonProperty("address")
	String address;

	@JsonProperty("entryDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss+00:00")
	Date entryDate;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Lead))
			return false;
		if (obj == this)
			return true;
		return this._id.trim().equals(((Lead) obj)._id.trim()) || this.email.trim().equalsIgnoreCase((((Lead) obj).email.trim()));
	}

	@Override
	public int hashCode() {
		return 0;
	}

}
