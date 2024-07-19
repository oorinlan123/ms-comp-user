package ec.nttdata.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

import ec.nttdata.user.util.ResultStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientFindResponse extends Response {

	private static final long serialVersionUID = 1L;
	private List<Client> clientList;
	
	/**
	 * @param response
	 */
	public ClientFindResponse(Response response) {
		super(response);
	}

	/**
	 * @param result
	 */
	public ClientFindResponse(ResultStatus result) {
		super(result);
	}
	
}
