package com.mediscreen.ms_note;

import com.mediscreen.ms_note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MsNoteApplicationTests {

	@MockBean
	private NoteRepository noteRepository;

	@Test
	void contextLoads() {
	}

}
