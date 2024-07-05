package org.example.testspringdata.data.asso1;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.testspringdata.data.asso2.*;
import org.example.testspringdata.data.asso3.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;


@Configuration
public class RepoConfiguration {

  @Bean
  public UserRepository userRepository(EntityManager em) {
    return new UserRepository(em, User.class);
  }

  @Bean
  public GroupRepository groupRepository(EntityManager em) {
    return new GroupRepository(em);
  }
  @Bean
  public CountryService countryService(CountryRepository countryRepository) {
    return new CountryService(countryRepository);
  }
  @Bean
  public RoomService roomService(RoomRepository roomRepository) {
    return new RoomService(roomRepository);
  }


  @Bean
  public ApplicationRunner test(UserRepository userRepository, CountryService countryService, RoomRepository roomRepository, RoomService roomService) {
    return args -> {
      System.out.println("Will get Countries");
      //System.out.println("And then wait for 2s");
      Instant now = Instant.now();
      countryService.printAll();
      System.out.println("TOOK "+(Instant.now().toEpochMilli() - now.toEpochMilli())+"ms to get all the countries");
      //Thread.sleep(2000);
      System.out.println("Will get Rooms");
      now = Instant.now();
      List<Chair> list = roomRepository.getRoomsOneRequest().stream().map(Room::getChairs).flatMap(Set::stream).toList();
      System.out.println("Found "+list.size()+" chairs");
      System.out.println("TOOK "+(Instant.now().toEpochMilli() - now.toEpochMilli())+"ms to get all the rooms");
      System.out.println("Will get Rooms with another way");
      now = Instant.now();
      List<Chair> chairs = roomService.getAllAnotherOneRequest().stream().map(Room::getChairs).flatMap(Set::stream).toList();
      System.out.println("Found "+chairs.size()+" chairs with another way");
      System.out.println("TOOK "+(Instant.now().toEpochMilli() - now.toEpochMilli())+"ms to get all the rooms");
      Thread.sleep(2000);
      System.out.println("-----------------------");
      System.out.println("Will get Groups");
      now = Instant.now();
      userRepository.getGroupAndUsers();//.forEach(groupAndUsers -> System.out.println("got a group "+groupAndUsers.group().getName()+" and "+groupAndUsers.users().size()+" users"));
      System.out.println("TOOK "+(Instant.now().toEpochMilli() - now.toEpochMilli())+"ms to get all the groups");
      //Thread.sleep(2000);

    };
  }

  //  @Bean
  //  public ApplicationRunner test(GroupRepository groupRepository, CountryService countryService)
  // {
  //    return args -> {
  //      System.out.println("Will get Countries");
  //      System.out.println("And then wait for 2s");
  //      Instant now = Instant.now();
  //      System.out.println(countryService.getAll().size()+" countries");
  //      System.out.println("TOOK "+(Instant.now().toEpochMilli() - now.toEpochMilli())+"ms to get
  // all the countries");
  //      Thread.sleep(2000);
  //      System.out.println("-----------------------");
  //      System.out.println("Will get Groups");
  //      now = Instant.now();
  //      System.out.println(groupRepository.findAll().size()+" groups");
  //      System.out.println("TOOK "+(Instant.now().toEpochMilli() - now.toEpochMilli())+"ms to get
  // all the groups");
  //      Thread.sleep(2000);
  //
  //    };
  //  }

//  @Bean
//  public ApplicationRunner insertData(
//      UserRepository userRepository,
//      GroupRepository groupRepository,
//      CountryRepository countryRepository,
//      CityRepository cityRepository,
//      ChairRepository chairRepository,
//      RoomRepository roomRepository) {
//    return args -> {
//      for (int i = 1; i <= 100; i++) {
//        System.out.println("Step " + i);
//        Group newGroup = new Group();
//        newGroup.setName("gruop" + i);
//        newGroup.setId((long) i);
//        Group saved = groupRepository.save(newGroup);
//        for (int j = 1; j <= 10; j++) {
//          User newU = new User();
//          newU.setName("user" + i + "and" + j);
//          newU.setGroupId(saved.getId());
//          newU.setId((long) i * 1000 + j);
//          userRepository.save(newU);
//        }
//        Country c = new Country();
//        c.setName("country" + i);
//        c.setId((long) i);
//        Country countrySaved = countryRepository.save(c);
//        for (int j = 1; j <= 10; j++) {
//          City ci = new City();
//          ci.setName("city" + i + "and" + j);
//          ci.setId((long) i * 1000 + j);
//          ci.setCountry(countrySaved);
//          cityRepository.save(ci);
//        }
//        Room r = new Room();
//        r.setName("room" + i);
//        r.setId((long) i);
//        Room roomSaved = roomRepository.save(r);
//        for (int j = 1; j <= 10; j++) {
//          Chair chair = new Chair();
//          chair.setName("chair" + i + "and" + j);
//          chair.setId((long) i * 1000 + j);
//          chair.setRoom(roomSaved);
//          chairRepository.save(chair);
//        }
//      }
//    };
//    }

}
