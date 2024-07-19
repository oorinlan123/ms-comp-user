package ec.nttdata.user.service;

import ec.nttdata.user.dto.Header;
import ec.nttdata.user.dto.ClientCreateUpdateRequest;
import ec.nttdata.user.dto.ClientFindResponse;
import ec.nttdata.user.dto.ClientParamRequest;
import ec.nttdata.user.dto.Response;

/**
 * Definicion de la interfaz del servicio de ubicaciones
 * 
 * @author Israel Guacho <mailto:israel.guachog@gmail.com>
 * @version 1.0
 * @since 27/12/2022
 * 
 */
public interface ClientService {
	
	public Response create(Header header, ClientCreateUpdateRequest request);
	
	public Response update(Header header, ClientParamRequest param, ClientCreateUpdateRequest request);
	
	public Response delete(Header header, ClientParamRequest param);
	
}
 