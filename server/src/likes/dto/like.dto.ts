import { IsNotEmpty } from 'class-validator';

export class CreateLikeDto {
  userId: string;

  @IsNotEmpty()
  tweetId: string;
}
