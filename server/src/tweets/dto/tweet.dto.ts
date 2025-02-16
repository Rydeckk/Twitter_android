import { PartialType } from '@nestjs/mapped-types';

export class LikeDto {
  tweetId: string;
  userId: string;
}

export class CreateTweetDto {
  content: string;
  userId: string;
}

export class UpdateTweetDto extends PartialType(CreateTweetDto) {}
