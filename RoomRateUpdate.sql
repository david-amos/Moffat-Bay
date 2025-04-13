-- Update all room rates by 5%
UPDATE Room SET  RoomPrice = (RoomPrice * 0.05) + RoomPrice;