import { Module } from '@nestjs/common';
import { TweetsService } from './tweets.service';
import { TweetsController } from './tweets.controller';
import { PrismaService } from 'src/prisma.service';
import { FollowsService } from 'src/follows/follows.service';

@Module({
  controllers: [TweetsController],
  providers: [TweetsService, PrismaService, FollowsService],
})
export class TweetsModule {}
