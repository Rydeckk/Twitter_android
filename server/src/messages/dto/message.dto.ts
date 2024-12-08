import { PartialType } from '@nestjs/mapped-types';
import { IsNotEmpty, IsString, IsUUID } from 'class-validator';

export class CreateMessageDto {
  @IsNotEmpty()
  @IsString()
  message: string;

  @IsNotEmpty()
  @IsUUID('4')
  conversationId: string;
}
export class UpdateMessageDto extends PartialType(CreateMessageDto) {}
