package ec.nttdata.user.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import ec.nttdata.user.util.EncryptionId;
import ec.nttdata.user.util.Uuid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class ClientParamRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "El campo 'code' es requerido")
	@Uuid(message = "El campo 'code' debe ser un uuid")
	private String code;
	
}
