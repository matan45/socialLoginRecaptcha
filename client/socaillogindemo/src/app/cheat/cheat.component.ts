import { Component, OnInit } from '@angular/core';
import { RxStompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-cheat',
  templateUrl: './cheat.component.html',
  styleUrls: ['./cheat.component.css']
})
export class CheatComponent implements OnInit {
  public receivedMessages: string[] = [];
  private topicSubscription: Subscription;

  constructor(private rxStompService: RxStompService) { }

  ngOnInit(): void {
    this.topicSubscription = this.rxStompService.watch('/chat').subscribe((message: Message) => {
      console.log(message.body)
      this.receivedMessages.push(message.body);
    });
  }

  onSendMessage() {
    const message = `Message generated at ${new Date}`;
    this.rxStompService.publish({ destination: '/app/send/message', body: message });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }

}
