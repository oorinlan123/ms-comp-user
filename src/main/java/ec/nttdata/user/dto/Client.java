package ec.nttdata.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "El campo 'identificationNumber' es requerido")
    @Size(min = 10, max = 13, message = "El campo 'identificationNumber' debe tener entre 10 a 13 caracteres")
    private String identificationNumber;
    
    @NotNull(message = "El campo 'password' es requerido")
    @Size(min = 2, max = 50, message = "El campo 'password' debe tener entre 2 o más caracteres")
    private String password;
    
    @NotNull(message = "El campo 'name' es requerido")
    @Size(min = 2, message = "El campo 'name' debe tener entre 3 o más caracteres")
    private String name;
    
    @NotNull(message = "El campo 'gender' es requerido")
    @Size(min = 2, message = "El campo 'gender' debe tener entre 3 o más caracteres")
    private String gender;
    
    @NotNull(message = "El campo 'age' es requerido")
    @Min(value = 1, message = "El valor mínimo para el campo 'age' debe ser mayor o igual a 1")
    private Integer age;
    
    @NotNull(message = "El campo 'address' es requerido")
    @Size(min = 2, message = "El campo 'address' debe tener entre 2 o más caracteres")
    private String address;
    
    @NotNull(message = "El campo 'phone' es requerido")
    @Size(min = 10, max = 13, message = "El campo 'phone' debe tener entre 10 a 13 caracteres")
    private String phone;

}
