import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { over } from '@stomp/stompjs';
import $ from 'jquery';


@Component({
  selector: 'app-cheat',
  templateUrl: './cheat.component.html',
  styleUrls: ['./cheat.component.css']
})
export class CheatComponent implements OnInit {
  private serverUrl = 'http://localhost:8080/socket'
  public title = 'WebSockets chat';
  private stompClient;

  constructor(){
    this.initializeWebSocketConnection();
  }

  ngOnInit(): void {
   
  }

  initializeWebSocketConnection(){
    this.stompClient = over(new SockJS(this.serverUrl));
    let that = this;
    this.stompClient.connect({}, function() {
      that.stompClient.subscribe("/chat", (message) => {
        if(message.body) {
          $(".chat").append("<div class='message'>"+message.body+"</div>")
          console.log(message.body);
        }
      });
    });
  }

  sendMessage(message){
    this.stompClient.send("/app/send/message" , {}, message);
    $('#input').val('');
  }

}
