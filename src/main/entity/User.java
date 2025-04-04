package entity;

import lombok.*;
import javax.validation.constraints.*;

@Data
@Builder



/*@Getter
@Setter
@ToString
@RequiredArgsConstructor*/


public class User {

    private int id;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    private int age;

}
