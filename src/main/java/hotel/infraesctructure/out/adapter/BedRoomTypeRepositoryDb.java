package hotel.infraesctructure.out.adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hotel.application.ports.BedRoomTypeRepositoryPort;
import hotel.domain.BedRoomType;
import hotel.infraesctructure.out.mapper.RowMapper;

public class BedRoomTypeRepositoryDb implements BedRoomTypeRepositoryPort {

    private final Connection connection;
    private final RowMapper bedRoomTypeRowMapper;

    public BedRoomTypeRepositoryDb(Connection connection,  RowMapper bedRoomTypeRowMapper) {
        this.connection = connection;
        this.bedRoomTypeRowMapper = bedRoomTypeRowMapper;
    }

    @Override
    public BedRoomType saveBedRoomType(BedRoomType bedRoomType) {
        String sql = "INSERT INTO bed_room_type (id_type, type) VALUES (?, ?)";


        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setBedRoomTypeParams(ps, bedRoomType);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el tipo de habitación", e);
        }

        return bedRoomType;
    }

    @Override
    public Optional<BedRoomType> findBedRoomTypeById(int id) {

        String sql = "SELECT * FROM bed_room_type WHERE id_type = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    BedRoomType bedRoomType = (BedRoomType) bedRoomTypeRowMapper.mapRow(rs);
                    return Optional.of(bedRoomType);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el tipo de habitación por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<BedRoomType> findAllBedRoomTypes() {
        List<BedRoomType> bedRoomTypes = new ArrayList<>();

        String sql = "SELECT * FROM bed_room_type";

        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BedRoomType bedRoomType = (BedRoomType) bedRoomTypeRowMapper.mapRow(rs);
                bedRoomTypes.add(bedRoomType);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la lista de tipos de habitaciones", e);
        }
    return bedRoomTypes;
    }

    private void setBedRoomTypeParams(PreparedStatement ps, BedRoomType bedRoomType) throws SQLException {
        ps.setInt(1, bedRoomType.getIdType());
        ps.setString(2, bedRoomType.getType());
    }
}