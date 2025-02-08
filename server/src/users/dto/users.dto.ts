import { Type } from 'class-transformer';
import { IsDate, IsOptional, IsString } from 'class-validator';

export class UpdateUserDto {
  @IsOptional()
  @IsString()
  username: string;

  @IsOptional()
  @IsString()
  biography: string;

  @IsOptional()
  @Type(() => Date)
  @IsDate()
  dateOfBirth: Date;
}
