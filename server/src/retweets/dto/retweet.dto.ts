import { RETWEET_TYPE } from '@prisma/client';
import { IsNotEmpty } from 'class-validator';

export class CreateRetweetDto {
  content: string;

  @IsNotEmpty()
  type: RETWEET_TYPE;

  userId: string;

  @IsNotEmpty()
  parentTweetId: string;
}
