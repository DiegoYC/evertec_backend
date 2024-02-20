package com.evertec.springboot2.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.evertec.springboot2.crud.model.Tarea;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TareaControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1";
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllTareas() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/tareas",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetTareaById() {
		Tarea tarea = restTemplate.getForObject(getRootUrl() + "/tareas/7", Tarea.class);
		System.out.println(tarea.getDescripcion());
		assertNotNull(tarea);
	}

	@Test
	public void testCreateTarea() {
		Tarea tarea = new Tarea();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		tarea.setDescripcion("Nueva tarea test.");
		tarea.setFechaCreacion(timestamp);
		tarea.setFechaModificacion(timestamp);
		tarea.setVigente(false);

		ResponseEntity<Tarea> postResponse = restTemplate.postForEntity(getRootUrl() + "/tareas", tarea, Tarea.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateTarea() {
		int id = 7;
		Tarea tarea = restTemplate.getForObject(getRootUrl() + "/tareas/" + id, Tarea.class);
		tarea.setDescripcion("Update Tarea");

		restTemplate.put(getRootUrl() + "/tareas/" + id, tarea);

		Tarea updatedTarea = restTemplate.getForObject(getRootUrl() + "/tareas/" + id, Tarea.class);
		assertNotNull(updatedTarea);
	}

	@Test
	public void testDeleteTarea() {
		int id = 1;
		Tarea tarea = restTemplate.getForObject(getRootUrl() + "/tareas/" + id, Tarea.class);
		assertNotNull(tarea);

		restTemplate.delete(getRootUrl() + "/tareas/" + id);

		try {
			tarea = restTemplate.getForObject(getRootUrl() + "/tareas/" + id, Tarea.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
