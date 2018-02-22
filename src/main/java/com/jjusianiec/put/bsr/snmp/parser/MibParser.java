package com.jjusianiec.put.bsr.snmp.parser;

import com.google.common.collect.Sets;
import com.jjusianiec.put.bsr.snmp.parser.model.MibTree;
import com.jjusianiec.put.bsr.snmp.parser.model.OIdRaw;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.joining;

public class MibParser {
    private final ImportsReader importsReader = new ImportsReader();
    private final MibFileWithoutCommentsReader mibFileWithoutCommentsReader = new MibFileWithoutCommentsReader();
    private final OIdReader oIdReader = new OIdReader();
    private final ObjectTypesReader objectTypeReader = new ObjectTypesReader();

    public MibTree parseMib(String path) {
        String content = mibFileWithoutCommentsReader.readFileWithoutComments(path);
        Map<String, List<String>> fileToImports = importsReader.readImports(content);
        Map<String, OIdRaw> identifierToOId = oIdReader.readIdentifiers(content);
        Map<String, OIdRaw> objectTypeIdToOId = objectTypeReader.readObjectTypes(content);

        String importContent = mibFileWithoutCommentsReader.readFileWithoutComments(getFile(fileToImports));
        Map<String, OIdRaw> importedIdentifierToOId = oIdReader.readIdentifiers(importContent);
        List<Map.Entry<String, OIdRaw>> knownOIds = newArrayList();
        for (Map.Entry<String, OIdRaw> stringOIdRawEntry : importedIdentifierToOId.entrySet()) {
            OIdRaw value = stringOIdRawEntry.getValue();
            if (value.getParentIdRaw().split(" ").length > 3) {
                String[] split = value.getParentIdRaw().replaceAll("iso", ".1.")
                        .replaceAll("[^\\d.]", ".").split("\\.");
                value.setParentIdNumber(Stream.of(split)
                        .filter(s -> s.length() > 0)
                        .collect(joining("."))
                );
                knownOIds.add(stringOIdRawEntry);
            }
        }
        for (int i = 0; i < importedIdentifierToOId.size(); i++) {
            for (int j = 0; j < importedIdentifierToOId.size(); j++) {
                for (Map.Entry<String, OIdRaw> stringOIdRawEntry : importedIdentifierToOId.entrySet()) {
                    if (stringOIdRawEntry.getValue().getParentIdRaw().contains(knownOIds.get(j).getKey())) {
                        if (!knownOIds.contains(stringOIdRawEntry)) {
                            knownOIds.add(stringOIdRawEntry);
                        }
                        String s = stringOIdRawEntry.getValue().getParentIdRaw().replaceAll("[^\\d.]", "");
                        stringOIdRawEntry.getValue().setParentIdNumber(knownOIds.get(j).getValue().getParentIdNumber() + "." + s);
                        if(knownOIds.get(j).getValue().getChildrenObjects() == null) {
                            knownOIds.get(j).getValue().setChildrenObjects(newHashSet());
                        }
                        knownOIds.get(j).getValue().getChildrenObjects().add(stringOIdRawEntry);
                    }
                }
            }
        }

        for (int i = 0; i < identifierToOId.size(); i++) {
            for (int j = 0; j < knownOIds.size(); j++) {
                for (Map.Entry<String, OIdRaw> stringOIdRawEntry : identifierToOId.entrySet()) {
                    if (stringOIdRawEntry.getValue().getParentIdRaw().contains(knownOIds.get(j).getKey())) {
                        if (!knownOIds.contains(stringOIdRawEntry)) {
                            knownOIds.add(stringOIdRawEntry);
                        }
                        String s = stringOIdRawEntry.getValue().getParentIdRaw().replaceAll("[^\\d.]", "");
                        stringOIdRawEntry.getValue().setParentIdNumber(knownOIds.get(j).getValue().getParentIdNumber() + "." + s);
                        if(knownOIds.get(j).getValue().getChildrenObjects() == null) {
                            knownOIds.get(j).getValue().setChildrenObjects(newHashSet());
                        }
                        knownOIds.get(j).getValue().getChildrenObjects().add(stringOIdRawEntry);
                    }
                }
            }
        }

        for (int i = 0; i < objectTypeIdToOId.size(); i++) {
            for (int j = 0; j < knownOIds.size(); j++) {
                for (Map.Entry<String, OIdRaw> stringOIdRawEntry : objectTypeIdToOId.entrySet()) {
                    if (stringOIdRawEntry.getValue().getParentIdRaw().contains(knownOIds.get(j).getKey())) {
                        if (!knownOIds.contains(stringOIdRawEntry)) {
                            knownOIds.add(stringOIdRawEntry);
                        }
                        String s = stringOIdRawEntry.getValue().getParentIdRaw().replaceAll("[^\\d.]", "");
                        stringOIdRawEntry.getValue().setParentIdNumber(knownOIds.get(j).getValue().getParentIdNumber() + "." + s);
                        if(knownOIds.get(j).getValue().getChildrenObjects() == null) {
                            knownOIds.get(j).getValue().setChildrenObjects(newHashSet());
                        }
                        knownOIds.get(j).getValue().getChildrenObjects().add(stringOIdRawEntry);
                    }
                }
            }
        }

        Map.Entry<String, OIdRaw> root = knownOIds.get(0);


        System.out.println(objectTypeIdToOId.entrySet().stream()
                .map(x -> x.getKey() + ":" + x.getValue().getParentIdRaw())
                .collect(Collectors.toList()).toString().replaceAll(",", "\n"));
        return null;
    }

    private String getFile(Map<String, List<String>> fileToImports) {
        Iterator<Map.Entry<String, List<String>>> iterator = fileToImports.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<String, List<String>> next = iterator.next();
            return "mibs/" + next.getKey();
        }
        return null;
    }
}
