package service;

import db.model.dynamic.DynamicTable;
import db.repository.dynamic.DynamicTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicService {
    @Autowired
    private DynamicTableRepository dynamicTableRepository;

    public boolean exists(Integer id) {
        DynamicTable dynamicTable;
        dynamicTable = dynamicTableRepository.findDynamicTable(id);
        if (dynamicTable == null)
            return false;
        return true;
    }
}
