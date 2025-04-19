-- Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas
-- Update all room rates by 5%
UPDATE Room SET  RoomPrice = (RoomPrice * 0.05) + RoomPrice;