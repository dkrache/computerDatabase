package publisher;

import javax.xml.ws.Endpoint;

import web.service.ComputerWebService;

//Endpoint publisher
public final class HelloWorldPublisher {

  private HelloWorldPublisher() {
    //
  }

  public static void main(String[] args) {
    Endpoint.publish("http://localhost:8081/computer-database-jaxws/computer",
        new ComputerWebService());
  }

}