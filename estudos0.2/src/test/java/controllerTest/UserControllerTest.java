package controllerTest;

import Templates.UserBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.estudos.EstudosApplication;
import com.test.estudos.model.User;
import com.test.estudos.repository.UserRepository;
import com.test.estudos.resources.UserController;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        User user = UserBuilder.getUser();
        mockMvc.perform(MockMvcRequestBuilders.post("/crud/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveBuscarUserPeloId() throws Exception{
        User user = UserBuilder.getUser();
        when(userService.buscarID(user.getId())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/crud/find/{id}",2)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void deveBuscarTodos() throws Exception {
        List<User> users = UserBuilder.getUsers();
        when(userRepo.findAll()).thenReturn(users);
        mockMvc.perform(MockMvcRequestBuilders.get("/crud/find-all"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveAlterarDadosDoUser() throws Exception{
        User user = UserBuilder.getUser();
        when(userService.buscarID(user.getId())).thenReturn(user);
        user.setIdade(20);
        when(userService.alteracao(5, user)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/crud/update/{id}", 5)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void deveDeletarUser() throws Exception{
        User user = new User(6,"Lucas", 25, "lucas@cadmus.com.br");
        when(userService.buscarID(user.getId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/crud/delete/{id}", 6)
                .contentType("aplication/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isNoContent());
    }
}
