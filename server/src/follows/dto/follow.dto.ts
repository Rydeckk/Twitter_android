import { IsNotEmpty, IsUUID } from 'class-validator';

class FollowDto {
  @IsNotEmpty()
  @IsUUID('4')
  followingId: string;
}

export class CreateFollowDto extends FollowDto {}

export class DeleteFollowDto extends FollowDto {}
