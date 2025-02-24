import { RETWEET_TYPE } from '@prisma/client';
import { IsNotEmpty } from 'class-validator';

export class CreateRetweetDto {
  @IsNotEmpty()
  content: string;

  @IsNotEmpty()
  type: RETWEET_TYPE;

  userId: string;

  @IsNotEmpty()
  parentTweetId: string;
}
