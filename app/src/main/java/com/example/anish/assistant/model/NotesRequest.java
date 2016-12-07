package com.example.anish.assistant.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by anish on 25-11-2016.
 */

public class NotesRequest extends Notes {
    private long NoteId;
    private String Title;
    private String Description;
    private String NoteDate;

    public long getNoteId() {
        return NoteId;
    }

    public void setNoteId(long noteId) {
        NoteId = noteId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNoteDate() {
        return NoteDate;
    }

    public void setNoteDate(String noteDate) {
        NoteDate = noteDate;
    }

    @Override
    public long NoteId() {
        return NoteId;
    }

    @NonNull
    @Override
    public String Title() {
        return Title;
    }

    @Nullable
    @Override
    public String Description() {
        return Description;
    }

    @Nullable
    @Override
    public String NoteDate() {
        return NoteDate;
    }

    @Override
    public String toString() {
        return "Notes{"
                + "NoteId=" + NoteId + ", "
                + "Title=" + Title + ", "
                + "Description=" + Description + ", "
                + "NoteDate=" + NoteDate
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Notes) {
            Notes that = (Notes) o;
            return (this.NoteId == that.NoteId())
                    && (this.Title.equals(that.Title()))
                    && ((this.Description == null) ? (that.Description() == null) : this.Description.equals(that.Description()))
                    && ((this.NoteDate == null) ? (that.NoteDate() == null) : this.NoteDate.equals(that.NoteDate()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (this.NoteId >>> 32) ^ this.NoteId;
        h *= 1000003;
        h ^= this.Title.hashCode();
        h *= 1000003;
        h ^= (Description == null) ? 0 : this.Description.hashCode();
        h *= 1000003;
        h ^= (NoteDate == null) ? 0 : this.NoteDate.hashCode();
        return h;
    }
}
