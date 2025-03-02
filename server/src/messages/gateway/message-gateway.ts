import { WebSocketGateway, WebSocketServer, SubscribeMessage, OnGatewayConnection, OnGatewayInit } from '@nestjs/websockets';
import { Server, Socket } from 'socket.io';

@WebSocketGateway({ cors: { origin: '*' }, transports: ['websocket'] }) 
export class MessagesGateway implements OnGatewayInit {
  @WebSocketServer()
  server: Server;

  afterInit(server: Server) {
    this.server = server;
  }

  @SubscribeMessage('joinConversation')
  handleJoinConversation(client: Socket, userId: string) {
    if (!userId) {
      return;
    }

    client.join(userId);
  }

  sendMessageToUser(userId: string, message: any) {
    if (!this.server) {
        console.error("ERREUR : this.server est undefined !");
        return;
    }

    this.server.to(userId).emit('newMessage', message);
  }
}