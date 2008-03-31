/*
   Copyright 2008 Chris Searle

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
class HutService {

    boolean transactional = true

    def visibleHuts(String hutgroup) {
        def criteria = Hut.createCriteria();

        if (hutgroup) {
            return criteria.listDistinct {
                and {
                    eq('openHut', true)
                    owner {
                        eq('id', Long.valueOf(hutgroup))
                    }
                }
            }
        }

        return criteria.listDistinct {
            eq('openHut', true)
        }
    }

    def visibleHuts(Person p, String hutgroup) {
        if (p.admin) {
            return Hut.list()
        }

        log.debug("Person ${p}, Group ${hutgroup}")

        def criteria = Hut.createCriteria();

        // I still believe that this should work but it is generating an invalid query
//        if (hutgroup) {
//            return criteria.listDistinct {
//                and {
//                    or {
//                        eq('openHut', true)
//                        users {
//                            users {
//                                eq('userId', p.userId)
//                            }
//                        }
//                        owner {
//                            eq('userId', p.userId)
//                        }
//                    }
//                    owner {
//                        eq('id', Long.valueOf(hutgroup))
//                    }
//                }
//            }
//        }

        def list = criteria.listDistinct {
            or {
                eq('openHut', true)
                users {
                    users {
                        eq('userId', p.userId)
                    }
                }
                owner {
                    eq('userId', p.userId)
                }
            }
        }

        log.debug("List ${list}")

        if (hutgroup) {
            def removals = [];

            list.each {hut -> if (hut.owner.id != Long.valueOf(hutgroup)) {removals.add(hut)}}

            log.debug("Removals ${list}")

            removals.each {hut -> list.remove(hut)}

            log.debug("List after removals ${list}")
        }

        return list;
    }
}
