package ec.nttdata.user.service;

import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.nttdata.core.db.entity.Clients;
import ec.nttdata.core.db.entity.Persons;
import ec.nttdata.core.db.repository.ClientsRepository;
import ec.nttdata.core.db.repository.PersonsRepository;
import ec.nttdata.user.dto.Header;
import ec.nttdata.user.dto.ClientCreateUpdateRequest;
import ec.nttdata.user.dto.ClientParamRequest;
import ec.nttdata.user.dto.Response;
import ec.nttdata.user.util.ResultStatus;
import lombok.extern.log4j.Log4j2;

/**
 * Clase que provee metodos de CRUD de las ubicaciones
 * 
 * @author Israel Guacho <mailto:israel.guachog@gmail.com>
 * @version 1.0
 * @since 27/12/2022
 *
 */
@Service
@Transactional
@Log4j2
public class ClientServiceImpl implements ClientService {

	private static final String METHOD = "Method : {}.";
	private static final String REQUEST = "Request : {}.";
	private static final String MESSAGEPROCESS = "Solicitud procesada en : {}  milisegundos.";
	private static final String MESSAGEERROR = "Lo sentimos, inténtelo nuevamente.";
	private static final String INTERNALMESSAGEERROR = "Lo sentimos, inténtelo nuevamente.";
	private static final String ACTIVE = "ACTIVO";
	private static final String INACTIVE = "INACTIVO";
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
	private PersonsRepository personsRepository;
	
	/**
	 * Creación de clientes
	 * 
	 * @author Israel Guacho <mailto:israel.guachog@gmail.com>
	 * @version 1.0
	 * @since 30/05/2022
	 *
	 * @param Header                               header
	 * @param ClientCreateUpdateRequest request
	 * @return Response
	 */
	@Override
	public Response create(Header header, ClientCreateUpdateRequest request) {

		Long startTime = System.currentTimeMillis();
		Date now = new Date();
		Response response = null;

		try {

			Clients client = new Clients();
			Persons person = new Persons();
					
			person.setCode(UUID.randomUUID().toString());
			client.setPass(request.getPassword());
			person.setName(request.getName());
			person.setGender(request.getGender());
			person.setIdentificationNumber(request.getIdentificationNumber());
			person.setAddress(request.getAddress());
			person.setPhone(request.getPhone());
			person.setStatus(ACTIVE);
			person.setCreateDate(now);
			person.setModifyDate(now);
			person.setCreateUser(header.getUser());
			person.setModifyUser(header.getUser());
			client.setPerson(person);
			
			personsRepository.saveAndFlush(person);
			clientsRepository.saveAndFlush(client);

			response = new Response(ResultStatus.CREATED);
			response.setDescription("Cliente creado con éxito");

		} catch (Exception e) {
			log.log(Level.ERROR, e);
			log.log(Level.ERROR, e + " -Line: " + e.getStackTrace()[0].toString());
			response = new Response(ResultStatus.INTERNAL_SERVER_ERROR);
			response.setDescription(INTERNALMESSAGEERROR);
		}
		Long endTime = System.currentTimeMillis();
		log.log(Level.INFO, MESSAGEPROCESS, (endTime - startTime));
		return response;
	}
	
	/**
	 * Actualización de cliente
	 * 
	 * @author Israel Jonathan Guacho Guaman <mailto:israel.guachog@gmail.com>
	 * @version 1.0
	 * @since 18/07/2024
	 *
	 * @param Header header
	 * @param String userId
	 * @param String locationCode
	 * @param LocationExceptionUpdateRequest request
	 * @return Response
	 */
	@Override
	public Response update(Header header, ClientParamRequest param, ClientCreateUpdateRequest request) {

		Long startTime = System.currentTimeMillis();
		Date now = new Date();
		Response response = null;

		try {
			
			Clients client = clientsRepository.findByCodeAndStatus(param.getCode(), ACTIVE);
			response = new Response(ResultStatus.NOT_UPDATED);
			response.setDescription("No existe el cliente a actualizar o se encuentra inactivo");
			if (client != null) {
				
				Persons person = personsRepository.findByCodeAndStatus(param.getCode(), ACTIVE);
				
						
				person.setName(request.getName());
				person.setGender(request.getGender());
				person.setAddress(request.getAddress());
				person.setPhone(request.getPhone());
				person.setStatus(ACTIVE);
				person.setModifyDate(now);
				person.setModifyUser(header.getUser());
				client.setPerson(person);
				
				personsRepository.saveAndFlush(person);
				clientsRepository.saveAndFlush(client);
				
				response = new Response(ResultStatus.UPDATED);
				response.setDescription("Cliente actualizado con éxito");
			}

		} catch (Exception e) {
			log.log(Level.ERROR, e);
			log.log(Level.ERROR, e + " -Line: " + e.getStackTrace()[0].toString());
			response = new Response(ResultStatus.INTERNAL_SERVER_ERROR);
			response.setDescription(INTERNALMESSAGEERROR);
		}
		Long endTime = System.currentTimeMillis();
		log.log(Level.INFO, MESSAGEPROCESS, (endTime - startTime));
		return response;
	}
	
	/**
	 * Eliminación de cliente
	 * 
	 * @author Israel Jonathan Guacho Guaman <mailto:israel.guachog@gmail.com>
	 * @version 1.0
	 * @since 18/07/2024
	 *
	 * @param Header header
	 * @param String userId
	 * @param String locationCode
	 * @return Response
	 */
	public Response delete(Header header, ClientParamRequest param) {
		
		Long startTime = System.currentTimeMillis();
		Date now = new Date();
		
		Response response = null;
		try {
			Clients client = clientsRepository.findByCodeAndStatus(param.getCode(), ACTIVE);
			response = new Response(ResultStatus.NOT_DELETED);
			response.setDescription("No existe la ubicación a eliminar o se encuentra inactivo");
			if (client != null) {
				Persons person = personsRepository.findByCodeAndStatus(param.getCode(), ACTIVE);
				
				person.setStatus(INACTIVE);
				person.setModifyDate(now);
				person.setModifyUser(header.getUser());
				
				client.setStatus(INACTIVE);
				client.setModifyDate(now);
				client.setModifyUser(header.getUser());
				
				personsRepository.saveAndFlush(person);
				clientsRepository.save(client);
				response = new Response(ResultStatus.DELETED);
				response.setDescription("Ubicación eliminada con éxito");
			}
		} catch (Exception e) {
			log.log(Level.ERROR, e);
			log.log(Level.ERROR, e + " -Line: " + e.getStackTrace()[0].toString());
			response = new Response(ResultStatus.INTERNAL_SERVER_ERROR);
			response.setDescription(INTERNALMESSAGEERROR);
		}
		
		Long endTime = System.currentTimeMillis();
		log.log(Level.INFO, MESSAGEPROCESS, (endTime - startTime));
		return response;
	}

}
