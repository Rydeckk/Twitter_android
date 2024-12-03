import { Module } from '@nestjs/common';
import { TweetsService } from './tweets.service';
import { TweetsController } from './tweets.controller';
import { PrismaService } from 'src/prisma.service';
import { APP_GUARD } from '@nestjs/core';
import { AuthGuard } from 'src/auth/auth.guard';

@Module({
  controllers: [TweetsController],
  providers: [
    {
      provide: APP_GUARD,
      useClass: AuthGuard,
    },
    TweetsService,
    PrismaService,
  ],
})
export class TweetsModule {}
