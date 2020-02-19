package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService ;

    @InjectMocks
    OwnerController ownerController ;

    @Mock
    Model model2 ;

    MockMvc mockMvc ;

    Set<Owner> owners = new HashSet<>();
    final String expectedView = "owners/index";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
        owners.add(new Owner());
        owners.add(Owner.builder().city("Aachen").telephone("+0491772186832").build());
    }

    @Test
    void listOwner() throws Exception{
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name(expectedView))
                .andExpect(model().attribute("owners",hasSize(2)));
    }

    @Test
    void listOwnerModelAttributes() throws Exception{

        when(ownerService.findAll()).thenReturn(owners);
        ArgumentCaptor<Set<Owner>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String controllerView = ownerController.listOwner(model2);

        verify(model2).addAttribute(eq("owners"),argumentCaptor.capture());
        Set<Owner> ownersSet = argumentCaptor.getValue();

        int expectedSize = 2;
        assertEquals(expectedSize , ownersSet.size());
        assertEquals(expectedView, controllerView);
    }

    @Test
    void listOwnerOtherPath() throws Exception{
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name(expectedView))
                .andExpect(model().attribute("owners",hasSize(2)));
    }
    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));
        verifyNoInteractions(ownerService);
    }
}