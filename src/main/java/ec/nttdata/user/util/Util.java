package ec.nttdata.user.util;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ec.nttdata.core.db.entity.Accounts;
import ec.nttdata.core.db.repository.AccountsRepository;
import ec.nttdata.user.dto.Header;
import ec.nttdata.user.dto.Response;
import lombok.extern.log4j.Log4j2;

/**
 * Clase que provee metodos de Util
 * 
 * @author Israel Guacho <mailto:israel.guachog@gmail.com>
 * @version 1.0
 * @since 16/12/2020
 *
 */

@Service
@Transactional
@Log4j2
public class Util {

	private static final String METHOD = "Method : {}.";
	private static final String REQUEST = "Request : {}.";
	private static final String MESSAGEPROCESS = "Solicitud procesada en : {}  milisegundos.";
	private static final String ACTIVE = "ACTIVO";
	private static final String TOPICNOTIFICATION = "KT_KAFKA_TOPIC_NOTIFICACION";
	private static final String DESCRIPTION = "description";
	private static final String LINE = " -Line: ";

	@Autowired
	private CallRest callRest;

}
