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

        def criteria = Hut.createCriteria();

        if (hutgroup) {
            return criteria.listDistinct {
                and {
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
                    owner {
                        eq('id', Long.valueOf(hutgroup))
                    }
                }
            }
        }

        return criteria.listDistinct {
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
    }
}
