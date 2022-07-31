package com.mediscreen.ms_note;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NoteServiceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/view/notes/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/add"));
    }


    @Test
    public void testGetUpdateForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/view/notes/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/update"));
    }
}
