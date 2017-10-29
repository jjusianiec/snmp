package parser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OId {
	private String rawId;
	private OIdSyntax syntax;
	private String access;
	private String status;
	private String description;
	private OIdType type;
}
