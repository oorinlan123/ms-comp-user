package ec.nttdata.user.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
public class ClientCreateUpdateRequest extends Client implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "El campo 'userId' es requerido")
	private String userId;
	
}
