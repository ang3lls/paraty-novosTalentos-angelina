package controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.estudos.EstudosApplication;
import com.test.estudos.model.User;
import com.test.estudos.repository.UserRepository;
import com.test.estudos.resources.UserController;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.test.estudos.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

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

    @MockBean
    private UserRepository userRepo;

    @Test
    public void deveCriarNovoUser() throws Exception {

        User userEsperado = new User( 1, "Ana", 22, "ana@cadmus.com.br");
        mockMvc.perform(MockMvcRequestBuilders.post("/crud/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userEsperado)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveBuscarUserPeloId() throws Exception{

        User user = new User( 2, "Malone", 25, "post.malone@cadmus.com.br");
        when(userService.buscarID(2)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/crud/find/{id}",2)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }


    @Test
    public void deveBuscarTodos() throws Exception {

        List<User> users = Arrays.asList(
                new User( 3, "rafaela", 20, "rafaela@cadmus.com.br"),
                new User(4, "Rafa", 20, "rafa@cadmus.com.br"));
        when(userRepo.findAll()).thenReturn(users);
        mockMvc.perform(MockMvcRequestBuilders.get("/crud/find-all"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveAlterarDadosDoUser() throws Exception{
        User user = new User( 5, "angelina", 19, "angelina@cadmus.com.br");

        given(userService.alteracao(user.getId(), user)).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/crud/update/{id}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect((ResultMatcher) jsonPath("name", is(user.getName())));



        //when(userService.alteracao(5, user)).thenReturn(user);
        //mockMvc.perform(MockMvcRequestBuilders.put("/crud/update/{id}")
                       // .contentType(MediaType.APPLICATION_JSON))
              //  .andExpect(status().isOk());
        //verify(userService, times(1)).findById(user.getId());
        //verify(userService, times(1)).update(user);
        //verifyNoMoreInteractions(userService);
    }

    private Object is(Object name) {
        return null;
    }
}
