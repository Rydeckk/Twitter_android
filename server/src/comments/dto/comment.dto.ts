import { IsNotEmpty } from 'class-validator';

export class CreateCommentDto {
  @IsNotEmpty()
  content: string;

  userId: string;

  @IsNotEmpty()
  parentTweetId: string;
}
