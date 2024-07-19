package ec.nttdata.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import ec.nttdata.user.dto.ClientCreateUpdateRequest;
import ec.nttdata.user.dto.ClientFindResponse;
import ec.nttdata.user.dto.ClientParamRequest;
import ec.nttdata.user.dto.Response;
import ec.nttdata.user.util.EncryptionId;

/**
 * Definicion de la interfaz del API de Clientes
 * 
 * @author Israel Guacho <mailto:israel.guachog@gmail.com>
 * @version 1.0
 * @since 18/07/2024
 */
public interface ClientApi {
	
	
	public ResponseEntity<Response> create(
			HttpServletRequest header,
			String channel,
			String user,
			String app,
			@Valid @RequestBody ClientCreateUpdateRequest request
	);
	
	public ResponseEntity<Response> update(
			HttpServletRequest header,String channel,
			String user,
			String app,
			@Valid @ModelAttribute ClientParamRequest param,
			@Valid @RequestBody ClientCreateUpdateRequest request
	);
	
	public ResponseEntity<Response> delete(
			HttpServletRequest header,
			String channel,
			String user,
			String app,
			@Valid @ModelAttribute ClientParamRequest param
	);

}
