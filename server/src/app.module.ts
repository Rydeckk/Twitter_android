import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AuthModule } from './auth/auth.module';
import { UsersModule } from './users/users.module';
import { TweetsModule } from './tweets/tweets.module';
import { ConversationsModule } from './conversations/conversations.module';
import { AuthGuard } from './auth/auth.guard';
import { APP_GUARD } from '@nestjs/core';
import { MessagesModule } from './messages/messages.module';
import { FollowsModule } from './follows/follows.module';

@Module({
  imports: [
    AuthModule,
    UsersModule,
    TweetsModule,
    ConversationsModule,
    MessagesModule,
    FollowsModule,
  ],
  controllers: [AppController],
  providers: [
    {
      provide: APP_GUARD,
      useClass: AuthGuard,
    },
    AppService,
  ],
})
export class AppModule {}
