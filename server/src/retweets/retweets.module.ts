import { Module } from '@nestjs/common';
import { RetweetsService } from './retweets.service';
import { RetweetsController } from './retweets.controller';
import { PrismaService } from 'src/prisma.service';

@Module({
  controllers: [RetweetsController],
  providers: [RetweetsService, PrismaService],
})
export class RetweetsModule {}
