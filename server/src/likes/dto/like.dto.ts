import { IsNotEmpty } from 'class-validator';

export class CreateLikeDto {
  userId: string;

  @IsNotEmpty()
  tweetId: string;
}

export class DeleteTweetLikeDto {
  userId: string;

  @IsNotEmpty()
  tweetId: string;

  @IsNotEmpty()
  likeId: string;
}
