package com.jjusianiec.put.bsr.snmp.parser.model;

import com.google.common.collect.Sets;
import com.jjusianiec.put.bsr.snmp.parser.OIdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OIdRaw {
    private String parentIdRaw;
    private String parentIdNumber;
    private String syntax;
    private String access;
    private String status;
    private String description;
    private String index;
    private OIdType type;
    private Set<Map.Entry<String, OIdRaw>> childrenObjects = newHashSet();
}
