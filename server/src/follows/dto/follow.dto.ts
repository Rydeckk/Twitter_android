export class CreateFollowDto {
  followedById: string;
  followingId: string;
}

export class UnfollowDto extends CreateFollowDto {}
