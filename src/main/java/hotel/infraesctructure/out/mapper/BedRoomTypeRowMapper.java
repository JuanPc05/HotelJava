package hotel.infraesctructure.out.mapper;

import hotel.domain.BedRoomType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BedRoomTypeRowMapper implements  RowMapper<BedRoomType>{

    @Override
    public BedRoomType mapRow(ResultSet rs) throws SQLException {

        BedRoomType bedRoomType = new BedRoomType();
        bedRoomType.setIdType(rs.getInt("id_type"));
        bedRoomType.setType(rs.getString("type"));
        return bedRoomType;
    }
}
