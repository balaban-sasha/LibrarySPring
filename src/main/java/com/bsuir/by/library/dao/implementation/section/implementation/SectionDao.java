package com.bsuir.by.library.dao.implementation.section.implementation;

import com.bsuir.by.library.bean.Section;
import com.bsuir.by.library.dao.AbstractDaoController;
import com.bsuir.by.library.dao.exception.DaoException;
import com.bsuir.by.library.dao.implementation.section.ISectionDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 28.03.2017.
 */
public class SectionDao extends AbstractDaoController<Section,Integer> implements ISectionDao {
    @Override
    public Section getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Section update(Section section) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<Section> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<Section> result = new LinkedList<Section>();
        try {
            while (rs.next()) {
                Section section = new Section();
                section.setDescription(rs.getString("lib_section_description"+dbDataLanguage));
                section.setHeader(rs.getString("lib_section_header"+dbDataLanguage));
                section.setId(rs.getInt("lib_section_id"));
                section.setName(rs.getString("lib_section_name"+dbDataLanguage));
                section.setNumber(rs.getInt("lib_section_number"));
                result.add(section);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String sectionName= String.valueOf(parameterMap.get("sectionName"));
        String sectionHeader = String.valueOf(parameterMap.get("sectionHeader"));
        String sectionDescription = String.valueOf(parameterMap.get("sectionDescription"));
        String sectionNameEn = String.valueOf(parameterMap.get("sectionNameEn"));
        String sectionHeaderEn = String.valueOf(parameterMap.get("sectionHeaderEn"));
        String sectionDescriptionEn = String.valueOf(parameterMap.get("sectionDescriptionEn"));
        String sectionNumber = String.valueOf(parameterMap.get("sectionNumber"));
        String sql = "INSERT INTO lib_section(lib_section_name,lib_section_header,lib_section_description,lib_section_name_en," +
                "lib_section_header_en,lib_section_description_en,lib_section_number)VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,sectionName);
        stmt.setString(2,sectionHeader);
        stmt.setString(3,sectionDescription);
        stmt.setString(4,sectionNameEn);
        stmt.setString(5,sectionHeaderEn);
        stmt.setString(6,sectionDescriptionEn);
        stmt.setInt(7,Integer.parseInt(sectionNumber));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap2, Connection connection, String dbDataLanguage) throws SQLException {
        String formData = String.valueOf(parameterMap2.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        String[] rowThatNeedToUpdate = String.valueOf(parameterMap2.get("checkBox")).split(",");
        JSONArray id = (JSONArray) answearObj.get("sectionId");
        JSONArray sectionName = (JSONArray) answearObj.get("sectionName");
        JSONArray sectionHeader = (JSONArray) answearObj.get("sectionHeader");
        JSONArray sectionDescription = (JSONArray) answearObj.get("sectionDescription");
        JSONArray sectionNumber = (JSONArray) answearObj.get("sectionNumber");
        String sql = "UPDATE lib_section SET lib_section_name"+dbDataLanguage+"=?,lib_section_description"+dbDataLanguage+"=?," +
                "lib_section_header"+dbDataLanguage+"=?,lib_section_number=? WHERE lib_section_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i);
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(sectionName.get(j)));
            stmt.setString(2, String.valueOf(sectionDescription.get(j)));
            stmt.setString(3, String.valueOf(sectionHeader.get(j)));
            stmt.setInt(4,Integer.parseInt(String.valueOf(sectionNumber.get(j))));
            stmt.setInt(5,Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = String.valueOf(parameterMap.get("checkBox")).split(",");
        String formData = String.valueOf(parameterMap.get("formData"));
        JSONObject answearObj = (JSONObject) JSONValue.parse(formData);
        JSONArray id = (JSONArray) answearObj.get("sectionId");
        String sql = "DELETE FROM lib_section WHERE lib_section_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(String.valueOf(id.get(j))));
            stmt.execute();

        }
    }
}
