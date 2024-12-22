import { RETWEET_TYPE } from '@prisma/client';
import { IsNotEmpty, IsString, IsUUID } from 'class-validator';

export class CreateRetweetDto {
  @IsNotEmpty()
  @IsString()
  content: string;

  type: RETWEET_TYPE;

  @IsNotEmpty()
  @IsUUID('4')
  parentTweetId: string;
}
