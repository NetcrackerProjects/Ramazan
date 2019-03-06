package interaction;

class InteractionType {

    private final int firstTypeId;
    private final int secondTypeId;

    InteractionType(int firstTypeId, int secondTypeId) {
        this.firstTypeId = firstTypeId;
        this.secondTypeId = secondTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InteractionType that = (InteractionType) o;
        return (firstTypeId == that.firstTypeId &&
                secondTypeId == that.secondTypeId) ||
                (firstTypeId == that.secondTypeId &&
                        secondTypeId == that.firstTypeId);
    }

    // https://stackoverflow.com/questions/15877914/java-overriding-equals-and-hashcode-for-two-interchangeable-integers
    @Override
    public int hashCode() {
        int res = 17;
        res = res * 31 + Math.min(firstTypeId, secondTypeId);
        res = res * 31 + Math.max(firstTypeId, secondTypeId);
        return res;
    }
}
