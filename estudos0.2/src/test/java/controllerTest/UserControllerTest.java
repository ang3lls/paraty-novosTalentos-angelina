package controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.estudos.EstudosApplication;
import com.test.estudos.model.User;
import com.test.estudos.resources.UserController;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.test.estudos.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= EstudosApplication.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void deveCriarNovoUser() throws Exception {

        User userEsperado = new User();
        userEsperado.setId(1);
        userEsperado.setName("Ana");
        userEsperado.setIdade(22);
        userEsperado.setEmail("ana@cadmus.com.br");

        mockMvc.perform(MockMvcRequestBuilders.post("/crud/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userEsperado)))
                .andExpect(status().isCreated());
    }

}

//when(userService.salvar(any(User.class))).thenReturn(userEsperado);

//User userASalvar = new User();
//userASalvar.setIdade(22);
//userASalvar.setName("Ana");

//mockMvc.perform(MockMvcRequestBuilders.post("/crud/salvar")
//  .contentType(MediaType.APPLICATION_JSON_UTF8)
// .accept(MediaType.APPLICATION_JSON_UTF8)
//  .content(JsonOutput.toJson(userASalvar)))
//  .andExpect(HttpStatus.CREATED(userService.salvar(User));
// .andExpect(content().json(JsonUtilis.toJason(userEsperado)));
