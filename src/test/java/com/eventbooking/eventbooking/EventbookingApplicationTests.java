package com.eventbooking.eventbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.eventbooking.eventbooking.Event.Dto.Request.CreateEventRequest;
import com.eventbooking.eventbooking.Event.Dto.Response.AdminEventResponse;
import com.eventbooking.eventbooking.Event.Dto.Response.EventResponse;
import com.eventbooking.eventbooking.Event.model.Event;
import com.eventbooking.eventbooking.Event.model.EventStatus;
import com.eventbooking.eventbooking.Event.repository.EventRepository;
import com.eventbooking.eventbooking.Event.service.EventService;

@ExtendWith(MockitoExtension.class)
class EventbookingApplicationTests {

	@Mock
	EventRepository eventrepo;

	@InjectMocks
	EventService eventservice;

	@Test
	void contextLoads() {
	}

	@Test
	void getActiveEventsForUsers()
	{
		//arrange
		Event e1 = new Event();
		e1.setArtistName("Ram"); 
		e1.setEventId(1);
		e1.setEventName("concert");
		e1.setVenue("address");
		e1.setStatus(EventStatus.ACTIVE);
		e1.setTotalCapacity(1000);
		e1.setBookedCount(550);
		Event e2 = new Event();
		e2.setEventId(2);
		e2.setArtistName("shyam");
		e2.setEventName("abcd");
		e2.setTotalCapacity(100);
		e2.setBookedCount(100);
		e2.setVenue("rheyayjtsrmwo");
		e2.setStatus(EventStatus.ACTIVE);
		when(eventrepo.findByStatusAndEndDateTimeAfterOrderByStartDateTimeAsc(any(),any())).thenReturn(List.of(e1,e2));
		//when(eventservice.getActiveEventsforUsers()).then

		//ACT

		List<EventResponse> response = eventservice.getActiveEventsforUsers();

		//assert 

		assertEquals(1, response.get(0).getEventId());
		assertEquals("Ram", response.get(0).getArtistName());
		assertEquals("concert", response.get(0).getEventName());
		assertEquals(450, response.get(0).getRemainingSeats());
		assertEquals(true, response.get(0).isBookable());
		assertEquals("address",response.get(0).getVenue());

		assertEquals(2, response.get(1).getEventId());
		assertEquals("shyam", response.get(1).getArtistName());
		assertEquals(0, response.get(1).getRemainingSeats());
		assertEquals(false, response.get(1).isBookable());
		assertEquals("rheyayjtsrmwo",response.get(1).getVenue());

	}

	@Test
	void createEvent()
	{
		//arrange
		CreateEventRequest request = new CreateEventRequest();
		AdminEventResponse resp = new AdminEventResponse();
		Event e1 = new Event();
		request.setArtistName("abcd");
		request.setEventName("kfsfo");
		request.setTotalCapacity(125);
		request.setVenue("fjwoiv street");
		LocalDateTime t1 = LocalDateTime.now().plusMonths(1);
		LocalDateTime t2 = LocalDateTime.now().plusHours(2).plusMonths(1);
		request.setStartDateTime(t1);
		request.setEndDateTime(t2);
		//when(eventservice.createEvent(request)).thenReturn(resp);
		//ACT
		AdminEventResponse response = eventservice.createEvent(request);
		//assert
		//assertEquals(1, response.getEventId());
		assertEquals("abcd", response.getArtistName());
		assertEquals("kfsfo", response.getEventName());
		assertEquals("fjwoiv street", response.getVenue());
		assertEquals(125, response.getTotalCapacity());
		assertEquals(EventStatus.ACTIVE, response.getStatus());
		assertEquals(t1, response.getStartDateTime());
		assertEquals(t2, response.getEndDateTime());
		assertEquals(0, response.getBookedCount());
		
	}
}
