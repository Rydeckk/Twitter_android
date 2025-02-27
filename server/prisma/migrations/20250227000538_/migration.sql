/*
  Warnings:

  - Changed the type of `entity_type` on the `voices` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.

*/
-- AlterEnum
ALTER TYPE "NOTIFICATION_TYPE" ADD VALUE 'TWEET';

-- AlterTable
ALTER TABLE "voices" DROP COLUMN "entity_type",
ADD COLUMN     "entity_type" "VOICE_ENTITY" NOT NULL;
