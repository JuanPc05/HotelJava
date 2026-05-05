package hotel.infraesctructure.out.mapper;

import hotel.domain.BedRoom;
import hotel.domain.BedRoomType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BedRoomRowMapper implements RowMapper<BedRoom> {
    @Override
    public BedRoom mapRow(ResultSet rs) throws SQLException {
        BedRoom bedRoom = new BedRoom();
        bedRoom.setRoomId(rs.getInt("room_id"));
        bedRoom.setRoom(rs.getString("room"));
        /*bedRoom.setBedRoomType(rs.getType(BedRoomType bedRoomType));*/
        bedRoom.setPrice(rs.getDouble("price"));
        bedRoom.setState(rs.getString("state"));
        return bedRoom;
    }
}
