import { PartialType } from '@nestjs/mapped-types';
import {
  ArrayNotEmpty,
  ArrayUnique,
  IsNotEmpty,
  IsUUID,
} from 'class-validator';

export class CreateConversationDto {
  @ArrayNotEmpty()
  @IsUUID('4', { each: true })
  @ArrayUnique()
  userIds: string[];
}

export class UpdateConversationDto extends PartialType(CreateConversationDto) {}

export class DeleteConversationDto {
  @IsNotEmpty()
  @IsUUID('4')
  conversationUserId: string;
}
