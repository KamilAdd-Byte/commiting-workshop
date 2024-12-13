module.exports = {
    parserOpts: {
        headerPattern: /^(\w+(?:\s\w+)*?)\s(feat|fix|chore|docs|refactor|style|test|perf|CWS\sfeat):\s(.*)$/,
        headerCorrespondence: ['prefix', 'type', 'subject'],
    },
    releaseRules: [
        { type: 'feat', release: 'minor' },
        { type: 'fix', release: 'patch' },
        { type: 'chore', release: false },
        { type: 'CWS feat', release: 'minor' },
    ],
    writerOpts: {
        groupBy: 'type',
        commitGroupsSort: 'title',
        commitsSort: ['scope', 'subject'],
    },
};
